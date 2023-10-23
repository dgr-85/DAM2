def dec_a_bin(txt_dec):
    dec = abs(int(txt_dec))
    res = ''
    base = 2

    while dec >= 1:
        res = str(dec % base) + res
        dec = dec // base
    return res if res != '' else txt_dec


numString = "77"
print("El valor absolut del número " + numString + " passat a binari és: " + dec_a_bin(numString))
