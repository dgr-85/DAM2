def codif(txt, clau):
    alfabet = 'abcdefghijklmnopqrstuvwxyz'
    txt_cod = ''
    for lletra in txt:
        if lletra.lower() in alfabet:
            lletra_nova = alfabet[(alfabet.index(lletra.lower()) + clau) % len(alfabet)]
            if lletra.isupper():
                lletra_nova = lletra_nova.upper()
            txt_cod = txt_cod + lletra_nova
        else:
            txt_cod = txt_cod + lletra
    return txt_cod


print(codif('Comprimint línees de codi... Èxit!', -1))
