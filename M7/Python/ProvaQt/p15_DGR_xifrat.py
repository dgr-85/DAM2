from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5 import uic
import sys


class Xifrador(QMainWindow):

    alfabet = 'abcdefghijklmnopqrstuvwxyz'

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p15_dgr_xifrat.ui', self)
        self.setWindowTitle('Xifrador de Cèsar')
        self.bt_xifrar.clicked.connect(lambda: self.codificar(self.te_desxifrat.toPlainText(), self.sb_clau.value(),
            True, self.cb_majuscules.isChecked()))
        self.bt_desxifrar.clicked.connect(lambda:
            self.codificar(self.te_xifrat.toPlainText(), self.sb_clau.value(), False, self.cb_majuscules.isChecked()))
        self.show()

    def codificar(self, txt, clau, xifrar, majuscules):
        if clau > len(self.alfabet):
            clau = len(self.alfabet)
            self.sb_clau.setValue(clau)
        if not xifrar:
            clau = -clau
        txt_cod = ''
        for lletra in txt:
            if lletra.lower() in self.alfabet:
                lletra_nova = self.alfabet[(self.alfabet.index(lletra.lower()) + clau) % len(self.alfabet)]
                if lletra.isupper() or majuscules:
                    lletra_nova = lletra_nova.upper()
                txt_cod = txt_cod + lletra_nova
            else:
                txt_cod = txt_cod + lletra
        if xifrar:
            self.te_xifrat.setPlainText(txt_cod)
        else:
            self.te_desxifrat.setPlainText(txt_cod)


app = QApplication(sys.argv)
win = Xifrador()
app.exec_()
