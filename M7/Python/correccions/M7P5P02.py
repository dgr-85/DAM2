def dec_a_bin(txt_dec):
    txt_bin = ""
    num = int(txt_dec)
    while num >= 2:
        res = str(num%2)
        txt_bin = str(res) + txt_bin
        num = num//2
    return "1" + txt_bin

def dec_a_hex(txt_dec):
    txt_bin = ""
    num = int(txt_dec)
    hex = "0123456789abcdef"
    while num >= 16:
        res = hex[(num%16)]
        txt_bin = hex[(num%16)] + txt_bin
        num = num//16
    return hex[(num%16)] + txt_bin

def llegir_document():
    while True:
        try:
            txt = input("Introdueix un nombre natural:")
            enter = int(txt)
            if (enter<0):
                raise ValueError("Torna a introduir un nombre valid")
            return enter
        except ValueError:
            print("Torna a introduir un nombre valid")

def programa_principal():
    while True:
        print("Introudueix una B si vols convertir un nombre a decimal")
        print("Introudueix una X si vols convertir un nombre a hexadecimal")
        print("Introduceix qualsevol caracter per sortir")
        opcio = input ("")
        if opcio == "B" or opcio == "b":
            enter = llegir_document()
            resultat = dec_a_bin(str(enter))
            print(resultat)
        elif opcio == "X" or opcio == "x":
            enter = llegir_document()
            resultat = dec_a_hex(str(enter))
            print(resultat)
        else:
            return

programa_principal()
