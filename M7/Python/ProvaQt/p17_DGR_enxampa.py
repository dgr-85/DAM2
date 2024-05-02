from PyQt5.QtWidgets import QApplication, QMainWindow
from PyQt5 import uic
import sys


class Enxampa(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p17_dgr_enxampa.ui',self)
        self.setWindowTitle("Enxampa'm")
        self.show()


app = QApplication(sys.argv)
win = Enxampa()
app.exec_()
