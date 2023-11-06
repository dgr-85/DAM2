def llegir_natural():
    while True:
        try:
            num = int(input("Introduce un número natural (entero positivo o cero): "))
            if num >= 0:
                return num
            else:
                print("Por favor, introduce un número natural (entero positivo o cero).")
        except ValueError:
            print("Error: Debes ingresar un número entero válido.")

def dec_a_bin(str_dec):
    num_dec = int(str_dec)
    num_bin = ""
    if num_dec == 0:
        return "0"
    while num_dec > 0:
        num_bin = str(num_dec % 2) + num_bin
        num_dec //= 2
    return num_bin

def dec_a_hex(str_dec):
    num_dec = int(str_dec)
    num_hex = ""
    if num_dec == 0:
        return "0"
    while num_dec > 0:
        residuo = num_dec % 16
        if residuo >= 10:
            num_hex = chr(ord('a') + residuo - 10) + num_hex
        else:
            num_hex = str(residuo) + num_hex
        num_dec //= 16
    return num_hex

while True:
    opcion = input("Elije una conversión (B para binario, X para hexadecimal, o cualquier otra tecla para salir): ").upper()

    if opcion == "B":
        numero_natural = llegir_natural()
        binario = dec_a_bin(str(numero_natural))
        print(f"El número {numero_natural} en binario es: {binario}")
    elif opcion == "X":
        numero_natural = llegir_natural()
        hexadecimal = dec_a_hex(str(numero_natural))
        print(f"El número {numero_natural} en hexadecimal es: {hexadecimal}")
    else:
        print("¡Hasta luego!")
        break