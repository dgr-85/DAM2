def dec_a_bin(txt_dec):
    dec = abs(int(txt_dec))
    res = ''
    base = 2

    while dec >= 1:
        res = str(dec % base) + res
        dec = dec // base
    return res if res != '' else txt_dec


def dec_a_hex(txt_dec):
    dec = abs(int(txt_dec))
    digits = "0123456789abcdef"
    base_hex = 16
    res = ''

    while dec >= base_hex:
        res = str(digits[dec % base_hex]) + res
        dec = dec // base_hex
    res = str(digits[dec]) + res
    return res


def llegir_natural():
    while True:
        try:
            txt=input("Introdueix un número enter positiu.")
            num_retornar=int(txt)
            if num_retornar > 0:
                return num_retornar
            else:
                print("El número ha de ser positiu.")
        except ValueError:
            print("Valor incorrecte. Has d'introduir un enter positiu.")


while True:
    operacio = input(
        "Indica què vols fer.\nB: Passar un número enter a binari\nX: Passar un número enter a hexadecimal\n"
        "(Per a sortir del programa, prem qualsevol altra tecla)\n")
    if operacio.upper() == "B":
        num = llegir_natural()
        str_num = str(num)
        print("El número " + str_num + " passat a binari és: " + dec_a_bin(str_num))
    elif operacio.upper() == "X":
        num = llegir_natural()
        str_num = str(num)
        print("El número " + str_num + " passat a hexadecimal és: " + dec_a_hex(str_num))
    else:
        print("No s'ha triat cap operació. Fi del programa.")
        break
