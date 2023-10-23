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


num = str(llegir_natural())
print("El número escollit és: " + num)
