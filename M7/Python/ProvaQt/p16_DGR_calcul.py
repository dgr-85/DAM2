from PyQt5.QtWidgets import QApplication, QMainWindow, QMessageBox
from PyQt5.QtCore import Qt
from PyQt5.QtGui import *
from PyQt5 import uic
import sys
import locale


class Calculadora(QMainWindow):

    val_enters = QIntValidator()
    val_decimals = QDoubleValidator()
    val_decimals.setNotation(QDoubleValidator.StandardNotation)
    caracters_maxims = 12
    rang_invalid = "Fora de rang"

    def __init__(self):
        super().__init__()
        uic.loadUi('ui/p16_dgr_calcul.ui', self)
        self.setWindowTitle('Calculadora')
        self.rb_decimal.setChecked(True)
        self.format_decimal()
        self.rb_suma.setChecked(True)
        self.le_num1.setMaxLength(Calculadora.caracters_maxims)
        self.le_num1.setAlignment(Qt.AlignRight)
        self.le_num2.setMaxLength(Calculadora.caracters_maxims)
        self.le_num2.setAlignment(Qt.AlignRight)
        self.le_resultat.setMaxLength(Calculadora.caracters_maxims)
        self.le_resultat.setAlignment(Qt.AlignRight)
        self.rb_decimal.toggled.connect(self.format_decimal)
        self.rb_enter.toggled.connect(self.format_enter)
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
            self.le_num1.setText(str(round(locale.atof(self.le_num1.text()))))
        if self.le_num2.text():
            self.le_num2.setText(str(round(locale.atof(self.le_num2.text()))))

    def validar_operands(self):
        if not self.le_num1.text() or not self.le_num2.text():
            self.finestra_error("Els operands no poden estar buits.")
            return
        if self.rb_decimal.isChecked():
            num1 = locale.atof(self.le_num1.text())
            num2 = locale.atof(self.le_num2.text())
        else:
            num1 = locale.atoi(self.le_num1.text())
            num2 = locale.atoi(self.le_num2.text())
        self.operar(num1, num2, self.rb_enter.isChecked())

    def operar(self, num1, num2, son_enters):
        resultat = ''
        if son_enters:
            format_sortida = "d"
        else:
            format_sortida = "g"
        if self.rb_suma.isChecked():
            resultat = locale.format_string("%" + format_sortida, num1 + num2)
        elif self.rb_resta.isChecked():
            resultat = locale.format_string("%" + format_sortida, num1 - num2)
        elif self.rb_producte.isChecked():
            resultat = locale.format_string("%" + format_sortida, num1 * num2)
        elif self.rb_divisio.isChecked():
            try:
                resultat = locale.format_string("%" + format_sortida, num1 / num2)
            except ZeroDivisionError:
                self.finestra_error("No es pot dividir per 0.")
        else:
            self.finestra_error("No s'ha seleccionat cap operació? Això no hauria de ser possible...")
        if len(resultat) > Calculadora.caracters_maxims:
            resultat = Calculadora.rang_invalid
        self.le_resultat.setText(resultat)

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
