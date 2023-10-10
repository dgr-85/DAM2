def dec_a_bin(txt_dec):
    dec = int(txt_dec)
    res = []
    while dec >= 1:
        res.insert(0, str(dec % 2))
        dec = dec//2
    return ''.join(res)


numString = "45"
print("El número " + numString + " passat a binari és: " + dec_a_bin(numString))
