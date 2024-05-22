import random
import time

from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox
from PyQt5 import uic, QtGui, QtCore
from PyQt5.QtGui import QPixmap
import sys


class Enxampa(QMainWindow):

    fantasma = 'ui/haunting.png'
    serp = 'ui/snake-tongue.png'
    imatge = fantasma
    boto_actual = (-2, -2)
    temps_total = 0
    punts_actuals = 0

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p17_dgr_enxampa.ui', self)
        self.setWindowTitle("Enxampa'm")
        self.qStackedWidget.setCurrentIndex(0)
        self.actionTauler.triggered.connect(self.canvi_pantalla)
        self.actionConfiguracio.triggered.connect(self.canvi_pantalla)
        self.actionSortir.triggered.connect(self.canvi_pantalla)
        self.actionQuant_a.triggered.connect(self.canvi_pantalla)
        self.comboBox.currentIndexChanged.connect(self.canvi_imatge_config)
        self.dimensions_botons(self.gridLayout)
        self.btn01.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn01))
        self.btn02.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn02))
        self.btn03.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn03))
        self.btn04.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn04))
        self.btn05.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn05))
        self.btn06.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn06))
        self.btn07.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn07))
        self.btn08.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn08))
        self.btn09.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn09))
        self.btn10.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn10))
        self.btn11.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn11))
        self.btn12.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn12))
        self.btn13.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn13))
        self.btn14.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn14))
        self.btn15.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn15))
        self.btn16.clicked.connect(lambda: self.imatge_boto(self.gridLayout, self.btn16))
        self.imatge_boto(self.gridLayout, None)
        Enxampa.punts_actuals = int(self.lbl_punts.text())
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
            self.lbl_imatge.setPixmap(QPixmap(Enxampa.fantasma))
            Enxampa.imatge = Enxampa.fantasma
        if self.comboBox.currentIndex() == 1:
            self.lbl_imatge.setPixmap(QPixmap(Enxampa.serp))
            Enxampa.imatge = Enxampa.serp

    def dimensions_botons(self, grid):
        for i in range(grid.rowCount()):
            for j in range(grid.columnCount()):
                boto = grid.itemAtPosition(i, j).widget()
                boto.setMinimumHeight(100)
                boto.setMinimumWidth(100)

    def imatge_boto(self, grid, boto):
        if Enxampa.punts_actuals < 10:
            if boto:
                fila = grid.indexOf(boto)
                columna = grid.getItemPosition(fila)
                coordenades = (columna[0], columna[1])
            else:
                coordenades = (-2, -2)
            if coordenades == Enxampa.boto_actual:
                (x, y) = (random.randint(0, grid.rowCount()-1), random.randint(0, grid.columnCount()-1))
                while (x, y) == Enxampa.boto_actual:
                    (x, y) = (random.randint(0, grid.rowCount()-1), random.randint(0, grid.columnCount()-1))
                nou_boto = grid.itemAtPosition(x, y).widget()
                nou_boto.setIcon(QtGui.QIcon(self.imatge))
                nou_boto.setIconSize(QtCore.QSize(100, 100))
                if Enxampa.boto_actual != (-2, -2):
                    grid.itemAtPosition(Enxampa.boto_actual[0], Enxampa.boto_actual[1]).widget().setIcon(QtGui.QIcon())
                    self.actualitzar_puntuacio_i_temps()
                Enxampa.boto_actual = (x, y)

    def actualitzar_puntuacio_i_temps(self):
        if Enxampa.punts_actuals == 0:
            Enxampa.temps_total = time.monotonic_ns()
        else:
            Enxampa.temps_total = time.monotonic_ns() - Enxampa.temps_total
        Enxampa.punts_actuals = Enxampa.punts_actuals + 1
        self.lbl_punts.setText(str(Enxampa.punts_actuals))
        if Enxampa.punts_actuals == 10:
            missatge = QMessageBox()
            missatge.setIcon(QMessageBox.Critical)
            missatge.setWindowTitle("Felicitats")
            missatge.setText("Has enxanmpat a tothom en " + str(int(Enxampa.temps_total / 1000000)) + " mil·lisegons!")
            missatge.setStandardButtons(QMessageBox.Ok)
            missatge.exec()


app = QApplication(sys.argv)
win = Enxampa()
app.exec_()
