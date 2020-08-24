import numpy as np
import matplotlib.pyplot as plt
from scipy.io import wavfile
import sounddevice as sd
from scipy import signal

def plotInTime(y, fs):
    plt.figure()
    N=len(y)
    t=np.arange(N)/fs
    plt.plot(t,y)
    plt.grid()
    plt.ylabel('Amplitude')

def plotInFrequency(y,fs):
    plt.figure()
    N=len(y)
    print(N)
    Y=np.fft.fft(y)
    Y=abs(Y)
    N=int(np.ceil(N/2))
    Y=Y[:N]
    f=np.arange(0,fs/2,fs/2/N)
    plt.plot(f,Y)
    plt.grid()
    plt.ylabel('Magnitude')
 
    
b,a=signal.butter(3,1,'low',analog=True)
w,h=signal.freqs(b,a)   
fs,y=wavfile.read(r'C:\Users\pvale\Desktop\cello.wav') 
plt.figure()
plotInTime(y,fs)
plotInFrequency(y,fs)
fil=signal.lfilter(b,a,y) 
sd.play(y,fs)



