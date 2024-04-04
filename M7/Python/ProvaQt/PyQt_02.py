from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5 import uic
import sys


class Exemple2(QMainWindow):

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/mainwindow.ui', self)
        self.show()


app = QApplication(sys.argv)
win = Exemple2()
app.exec_()
