from PyQt5.QtWidgets import QApplication, QMainWindow
import sys


class Exemple1(QMainWindow):

    def __init__(self):
        super().__init__()
        self.setGeometry(200, 200, 600, 300)
        self.setWindowTitle('Exemple 1')
        self.show()


app = QApplication(sys.argv)
win = Exemple1()
app.exec_()
