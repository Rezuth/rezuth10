import numpy as np
import matplotlib.pyplot as plt

def myCosine(A,f,ph0,t0,t1,fs):
    tVector=np.arange(t0, t1, 1/fs)
    x=A*np.cos(2*np.pi*f*tVector+ph0)
    plt.plot(tVector,x)
    plt.title('Cosine waveform')
    plt.figure()
    return x

def plotInTime(y,fs):
    N=len(y)
    t=np.arange(N)/fs
    plt.plot(t,y)
    plt.grid()
    plt.title('Amplitude variation')
    plt.xlabel('Time')
    plt.ylabel('Amplitude')
    plt.figure()
    
def plotInFrequency(y,fs):
    N=len(y)
    Y=np.fft.fft(y)
    Y=abs(Y)
    N=int(np.ceil(N/2))
    Y=Y[:N]
    f=np.arange(0,fs/2,fs/2/N)
    plt.plot(f,Y)
    plt.grid()
    plt.title('Magnitude Spectrum(first version)')
    plt.xlabel('Frequency')
    plt.ylabel('Magnitude')
    plt.figure()
    
x=np.array([1,-2,0,1])
X=np.fft.fft(x)
y=myCosine(2,5,0,1,2,100)  #A=2,f=5,ph0=0,t0=1,t1=2,fs=100
plotInTime(y,100)
plotInFrequency(y,100)
plt.title('Magnitude Spectrum(second version)')
plt.magnitude_spectrum(y,100)
plt.grid()
plt.figure()
plt.title('Phase Spectrum')
plt.phase_spectrum(y,100)
plt.grid()

#We have 2 versions for the magnitude,the first one is computted manually
#,and the second one is provided by the matplot library

