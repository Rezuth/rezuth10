import numpy as np
import scipy.signal as sp
import matplotlib.pyplot as plt
import math
import cmath


X=[1.+1.j, 2.+3.j, 1.+2.j, 3.+2.j]
H=[2.+3.j, 1.+1.j, 3.+3.j, 4.+5.j]

Y=np.array(X)*np.array(H)
y=np.fft.ifft(Y)
x=np.fft.ifft(X)
h=np.fft.ifft(H)

print(y==sp.convolve(np.hstack((x[1:], x)),h,'valid'))

#the reciprocal is verified
