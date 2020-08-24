from scipy import signal
import matplotlib.pyplot as plt
import math


plt.axis([0,5,0,1])
for N in range(1,9):
    b,a=signal.butter(N,1,'low',analog=True) # '1' is the cutoff frequency
    print(b)
    w,h=signal.freqs(b,a)
    plt.plot(w,(abs(h)))
    plt.title('Butterworth LP Filter Characteristics')
    plt.xlabel('Normalized frequency $\omega=\omega_c$')
    plt.ylabel('Amplitude')
plt.grid()
plt.show()