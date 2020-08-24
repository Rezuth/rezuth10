from scipy import signal
import matplotlib.pyplot as plt
import numpy as np

#t=np.arange(0, 1, 1/1000)
#plt.plot(t,signal.sawtooth(2 * np.pi * 5*t))

def mySawtooth(t0,t1,fs,f):
    t=np.arange(t0, t1, 1/fs)
    s=signal.sawtooth(2*np.pi*f*t)
    plt.plot(t,s)
    plt.title('Sawtooth waveform')
    plt.xlabel('Time(s)')
    plt.ylabel('Amplitude')
    plt.figure()
    
mySawtooth(0,1,1000,5)

def mySquare(t0,t1,fs,f):
    t=np.arange(t0, t1, 1/fs)
    s=signal.square(2*np.pi*f*t)
    plt.plot(t,s)
    plt.title('Square waveform')
    plt.xlabel('Time(s)')
    plt.ylabel('Amplitude')
    plt.figure()
    
mySquare(0,1,1000,5)

def myChirp(t0,t1,fs,f0,f1):
    t=np.arange(t0, t1, 1/fs)
    c=signal.chirp(t,f0,f1,t1)
    plt.plot(t,c)
    plt.title('Chirp waveform')
    plt.xlabel('Time(s)')
    plt.ylabel('Amplitude')
    plt.figure()
    
myChirp(0,5,1000,0,6)

def mySinc(x0,x1,x):
    xVector=np.linspace(x0,x1,x)
    plt.plot(xVector,np.sinc(xVector))
    plt.title('Sinc function')
    plt.figure()
    
mySinc(-5,5,100)

def myStairs(x0,x1,x):
    xVector=np.arange(x0,x1,1/x)
    y=np.sin(xVector)
    plt.step(xVector,y)
    plt.xlim(1,5)
    plt.ylim(-1,1)
    plt.figure()
    
myStairs(1,5,2)

def mySine(A,f,ph0,t0,t1,t):
    tVector=np.arange(t0, t1, 1/t)
    x=A*np.sin(2*np.pi*f*tVector+ph0)
    plt.plot(tVector,x)
    plt.figure()
       
mySine(5,2,0,1,2,100)

def myCosine(A,f,ph0,t0,t1,t):
    tVector=np.arange(t0, t1, 1/t)
    x=A*np.cos(2*np.pi*f*tVector+ph0)
    plt.plot(tVector,x)
       
myCosine(5,2,0,1,2,100)
       
       
       
       
       
       
       
       
       
       
       
       
    