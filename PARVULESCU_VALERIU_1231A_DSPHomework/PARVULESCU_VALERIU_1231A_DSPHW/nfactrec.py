def nFactRec(n):
    if(n==0):
        return 1
    else:
        return n*nFactRec(n-1)
    
n = int(input("Input\n"))
print(nFactRec(n))
        