import numpy as np
import matplotlib.pyplot as plt


mu,sigma=1, 1 #mean and standard deviation
s=np.random.normal(mu, sigma, 1000)
t=np.random.uniform(1,1000,1000)
print(s)
print(t)
plt.plot(s)
plt.figure()
plt.hist(s,30)
plt.figure()
plt.hist(t,30)

#the histogram of the uniform set is more dense than the other one
