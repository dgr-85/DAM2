from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox, QPushButton
from PyQt5.QtCore import Qt
from PyQt5.QtGui import *
from PyQt5 import uic
import sys
import locale


class Calculadora(QMainWindow):

    val_enters = QIntValidator()
    val_decimals = QDoubleValidator()
    val_decimals.setNotation(QDoubleValidator.StandardNotation)

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p16_dgr_calcul.ui', self)
        self.setWindowTitle('Calculadora')
        self.rb_decimal.setChecked(True)
        self.rb_suma.setChecked(True)
        self.le_num1.setMaxLength(12)
        self.le_num1.setAlignment(Qt.AlignRight)
        self.le_num2.setMaxLength(12)
        self.le_num2.setAlignment(Qt.AlignRight)
        self.le_resultat.setMaxLength(12)
        self.le_resultat.setAlignment(Qt.AlignRight)
        self.rb_decimal.clicked.connect(self.format_decimal)
        self.rb_enter.clicked.connect(self.format_enter)
        self.le_resultat.setReadOnly(True)
        self.bt_calcular.clicked.connect(self.validar_operands)
        self.show()

    def format_decimal(self):
        self.le_num1.setValidator(Calculadora.val_decimals)
        self.le_num2.setValidator(Calculadora.val_decimals)

    def format_enter(self):
        self.le_num1.setValidator(Calculadora.val_enters)
        self.le_num2.setValidator(Calculadora.val_enters)
        if self.le_num1.text():
            self.le_num1.setText(str(round(locale.atoi(self.le_num1.text()))))
        if self.le_num2.text():
            self.le_num2.setText(str(round(locale.atoi(self.le_num2.text()))))

    def validar_operands(self):
        num1 = 0
        num2 = 0
        if not self.le_num1.text() or not self.le_num2.text():
            self.finestra_error("Els operands no poden estar buits.")
            return
        if self.rb_decimal.isChecked():
            try:
                num1 = locale.atof(self.le_num1.text())
                num2 = locale.atof(self.le_num2.text())
            except ValueError:
                self.finestra_error("Els operands han de ser números vàlids.")
                return
        else:
            try:
                num1 = locale.atoi(self.le_num1.text())
                num2 = locale.atoi(self.le_num2.text())
            except ValueError:
                self.finestra_error("Els operands han de ser números vàlids.")
                return
        self.operar(num1, num2, self.rb_enter.isChecked)

    def operar(self, num1, num2, enter):
        if enter:
            format_sortida = "d"
        else:
            format_sortida = "g"
        if self.rb_suma.isChecked():
            self.le_resultat.setText(locale.format_string("%"+format_sortida, num1 + num2))
        elif self.rb_resta.isChecked():
            self.le_resultat.setText(locale.format_string("%"+format_sortida, num1 - num2))
        elif self.rb_producte.isChecked():
            self.le_resultat.setText(locale.format_string("%"+format_sortida, num1 * num2))
        else:
            try:
                self.le_resultat.setText(locale.format_string("%"+format_sortida, num1 / num2))
            except ZeroDivisionError:
                self.finestra_error("No es pot dividir por 0.")

    def finestra_error(self, text):
        missatge = QMessageBox()
        missatge.setIcon(QMessageBox.Critical)
        missatge.setWindowTitle("Error")
        missatge.setText(text)
        missatge.setStandardButtons(QMessageBox.Ok)
        missatge.exec()


app = QApplication(sys.argv)
locale.setlocale(locale.LC_ALL, "")
win = Calculadora()
app.exec_()
