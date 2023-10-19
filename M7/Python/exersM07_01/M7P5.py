def dec_a_bin(txt_dec):
    dec = abs(int(txt_dec))
    res = []
    base = 2

    while dec >= base:
        res.insert(0, str(dec % base))
        dec = dec // base
    return ''.join(res)


numString = "77"
print("El valor absolut del número " + numString + " passat a binari és: " + dec_a_bin(numString))
