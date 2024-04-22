from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5 import uic
import sys


class Xifrador(QMainWindow):

    alfabet = 'abcdefghijklmnopqrstuvwxyz'

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p15_dgr_xifrat.ui', self)
        self.setWindowTitle('Xifrador de Cèsar')
        self.sb_clau.setValue(11)
        self.sb_clau.setMinimum(0)
        self.sb_clau.setMaximum(len(self.alfabet) - 1)
        self.bt_xifrar.clicked.connect(self.determinar_funcio)
        self.bt_desxifrar.clicked.connect(self.determinar_funcio)
#        self.bt_xifrar.clicked.connect(lambda: self.codificar(self.te_desxifrat.toPlainText(), self.sb_clau.value(),
#            True, self.cb_majuscules.isChecked()))
#        self.bt_desxifrar.clicked.connect(lambda:
#            self.codificar(self.te_xifrat.toPlainText(), self.sb_clau.value(), False, self.cb_majuscules.isChecked()))
        self.show()

    def determinar_funcio(self):
        if self.sender().objectName() == "bt_xifrar":
            self.codificar(self.te_desxifrat.toPlainText(), self.sb_clau.value(), True, self.cb_majuscules.isChecked())
        else:
            self.codificar(self.te_xifrat.toPlainText(), self.sb_clau.value(), False, self.cb_majuscules.isChecked())

    def codificar(self, txt, clau, xifrar, majuscules):
        if not xifrar:
            clau = -clau
        txt_cod = ''
        for lletra in txt:
            if lletra.lower() in Xifrador.alfabet:
                lletra_nova = Xifrador.alfabet[(Xifrador.alfabet.index(lletra.lower()) + clau) % len(Xifrador.alfabet)]
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
