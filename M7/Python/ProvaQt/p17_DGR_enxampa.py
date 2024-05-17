import random

from PyQt5.QtWidgets import QApplication, QMainWindow, QPushButton
from PyQt5 import uic, QtGui, QtCore
from PyQt5.QtGui import QPixmap
import sys


class Enxampa(QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p17_dgr_enxampa.ui', self)
        self.setWindowTitle("Enxampa'm")
        #self.btn07.setIcon(QtGui.QIcon('ui/snake-tongue.png'))
        #self.btn07.setIconSize(QtCore.QSize(100, 100))
        self.qStackedWidget.setCurrentIndex(0)
        self.actionTauler.triggered.connect(self.canvi_pantalla)
        self.actionConfiguracio.triggered.connect(self.canvi_pantalla)
        self.actionSortir.triggered.connect(self.canvi_pantalla)
        self.actionQuant_a.triggered.connect(self.canvi_pantalla)
        self.comboBox.currentIndexChanged.connect(self.canvi_imatge_config)
        self.dimensions_botons(self.gridLayout)
        self.primer_boto(self.gridLayout)
        self.show()

    def canvi_pantalla(self):
        if self.sender().objectName() == "actionTauler":
            self.qStackedWidget.setCurrentIndex(0)
        elif self.sender().objectName() == "actionConfiguracio":
            self.qStackedWidget.setCurrentIndex(1)
        elif self.sender().objectName() == "actionSortir":
            app.quit()
        elif self.sender().objectName() == "actionQuant_a":
            self.qStackedWidget.setCurrentIndex(2)

    def canvi_imatge_config(self):
        if self.comboBox.currentIndex() == 0:
            self.lbl_imatge.setPixmap(QPixmap('ui/haunting.png'))
        if self.comboBox.currentIndex() == 1:
            self.lbl_imatge.setPixmap(QPixmap('ui/snake-tongue.png'))

    def dimensions_botons(self, grid):
        for i in range(grid.rowCount()):
            for j in range(grid.columnCount()):
                grid.itemAtPosition(i, j).widget().setMinimumHeight(100)
                grid.itemAtPosition(i, j).widget().setMinimumWidth(100)

    def primer_boto(self, grid):
        x = random.randint(0, grid.rowCount())
        y = random.randint(0, grid.columnCount())
        grid.itemAtPosition(x, y).widget().setIcon(QtGui.QIcon('ui/snake-tongue.png'))
        grid.itemAtPosition(x, y).widget().setIconSize(QtCore.QSize(100, 100))


app = QApplication(sys.argv)
win = Enxampa()
app.exec_()
