from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5 import uic
import sys


class Calculadora(QMainWindow):

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p16_dgr_calcul.ui', self)
        self.setWindowTitle('Calculadora')
        self.show()


app = QApplication(sys.argv)
win = Calculadora()
app.exec_()
