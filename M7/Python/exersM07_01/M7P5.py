def dec_a_bin(txt_dec):
    dec = abs(int(txt_dec))
    if dec == 0:
        return '0'
    else:
        res = []
        while dec >= 1:
            res.insert(0, str(dec % 2))
            dec = dec // 2
        return ''.join(res)


numString = "-72"
print("El valor absolut del número " + numString + " passat a binari és: " + dec_a_bin(numString))
