from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox
from PyQt5 import uic
from PyQt5.QtCore import Qt
from PyQt5.QtGui import *
import sys


class Quadrat(QMainWindow):

    val_enters = QIntValidator()
    res_fila1 = 0
    res_fila2 = 0
    res_fila3 = 0
    res_col1 = 0
    res_col2 = 0
    res_col3 = 0

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/pe4_dgr_examen.ui', self)
        self.setWindowTitle("Quadrat màgic")
        self.qStackedWidget.setCurrentIndex(0)
        self.actionTauler.triggered.connect(self.canvi_pantalla)
        self.actionConfiguracio.triggered.connect(self.canvi_pantalla)
        self.actionSortir.triggered.connect(self.canvi_pantalla)
        self.actionQuant_a.triggered.connect(self.canvi_pantalla)
        self.config_res_no_editables(self.gridLayout)
        self.formatar_enters(self.gridLayout)
        self.config_spin_boxes()
        self.btn_validar.clicked.connect(lambda: self.validar_camps(self.gridLayout))
        self.btn_reiniciar.clicked.connect(lambda: self.reiniciar(self.gridLayout))
        self.show()

    def config_res_no_editables(self, grid):
        for i in range(grid.rowCount()):
            for j in range(grid.columnCount()):
                item = grid.itemAtPosition(i, j)
                if (i == (grid.rowCount() - 1) or j == (grid.columnCount() - 1)) and item:
                    grid.itemAtPosition(i, j).widget().setReadOnly(True)

    def formatar_enters(self, grid):
        for i in range(self.gridLayout.rowCount()):
            for j in range(self.gridLayout.columnCount()):
                if i < grid.rowCount() - 1 or j < grid.rowCount() - 1:
                    grid.itemAtPosition(i, j).widget().setValidator(Quadrat.val_enters)

    def config_spin_boxes(self):
        self.spinBox_min.setValue(1)
        self.spinBox_max.setValue(9)
        self.spinBox_min.setMinimum(1)
        self.spinBox_min.setMinimum(1)
        self.spinBox_min.setMaximum(50)
        self.spinBox_min.setMaximum(50)

    def canvi_pantalla(self):
        if self.sender().objectName() == "actionTauler":
            self.qStackedWidget.setCurrentIndex(0)
        elif self.sender().objectName() == "actionConfiguracio":
            self.qStackedWidget.setCurrentIndex(1)
        elif self.sender().objectName() == "actionSortir":
            app.quit()
        elif self.sender().objectName() == "actionQuant_a":
            self.qStackedWidget.setCurrentIndex(2)

    def validar_camps(self, grid):
        camps = [0, 0, 0, 0, 0, 0, 0, 0, 0]
        camps_valids = True
        i = 0
        while i < grid.rowCount() - 1 and camps_valids:
            j = 0
            while j < grid.columnCount() - 1 and camps_valids:
                camp = grid.itemAtPosition(i, j).widget().text()
                if not camp:
                    self.finestra("Error", "S'han d'informar totes les caselles")
                    camps_valids = False
                camps[i] = camp
                j += 1
            i += 1
        # Falta validar que els valors encaixin amb els límits dels spinners de la pantalla de configuració
        # Falta validar que no hi hagi valors repetits
        if camps_valids:
            self.calcular(grid)

    def calcular(self, grid):
        res_files = [0, 0, 0]
        res_columnes = [0, 0, 0]
        for i in range(grid.rowCount() - 1):
            res = 0
            for j in range(grid.columnCount() - 1):
                res = res + int(grid.itemAtPosition(i, j).widget().text())
                res_files[i] = res
        for i in range(grid.rowCount() - 1):
            res = 0
            for j in range(grid.columnCount() - 1):
                res = res + int(grid.itemAtPosition(j, i).widget().text())
                res_columnes[i] = res
        print(res_files)
        print(res_columnes)
        # Els valors es calculen correctament i es mostren a res_files i res_columnes,
        # però falta imprimir-los al respectius camps de la pantalla

        # Falta validar que totes les sumes tinguin el mateix resultat

    def reiniciar(self, grid):
        for i in range(grid.rowCount() - 1):
            for j in range(grid.columnCount() - 1):
                grid.itemAtPosition(i, j).widget().setText('')

    def finestra(self, titol, text):
        missatge = QMessageBox()
        missatge.setIcon(QMessageBox.Critical)
        missatge.setWindowTitle(titol)
        missatge.setText(text)
        missatge.setStandardButtons(QMessageBox.Ok)
        missatge.exec()


app = QApplication(sys.argv)
win = Quadrat()
app.exec_()
