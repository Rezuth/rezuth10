import numpy as np
import matplotlib.pyplot as plt
import math

def sampleFunction(functionString,t0,t1,fs):
      tVector=np.arange(start=t0, stop=t1, step=1/fs, dtype='float')
      t=t0
      y=np.zeros(tVector.shape)
      for i in range(0,len(tVector)):
         t=tVector[i]
         y[i]=eval(functionString)
      return y,tVector

t0=0
t1 =1.5
fs=50
thold=.1
functionString='math.sin(t**3)/2**math.tan(t)'
y,t=sampleFunction(functionString,t0,t1,fs)
plt.plot(t,y)
plt.xlabel('time')
plt.ylabel('Amplitude')