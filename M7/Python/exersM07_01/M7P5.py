def dec_a_bin(txt_dec):
    dec=int(txt_dec)
    resultat=[]
    while dec//2>2:
        dec//2
        resultat.insert(0,str(dec%2))
    return resultat.join(resultat)

resFinal=dec_a_bin("45")
print(resFinal)
