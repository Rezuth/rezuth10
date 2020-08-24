import math
import cmath

s1=cmath.exp(1j*math.pi*(4/6)) #k=1
s2=cmath.exp(1j*math.pi*(6/6)) #k=2
s3=cmath.exp(1j*math.pi*(8/6)) #k=3
print('Analog transfer function is: H(s)= 1/(','(s+',s1,') ','(s+',s2,') ','(s+',s3,'))')