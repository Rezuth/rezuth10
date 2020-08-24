import math

pi=3.1416
frac,whole=math.modf(pi)
whole=int(whole)
i1=whole%2
whole=int(whole/2)

i2=whole%2
whole=int(whole/2)

i3=whole%2
whole=int(whole/2)

i4=whole%2
whole=int(whole/2)

x1=math.pow(2,1)
y1=1/x1
if(y1 > 0.12 and y1 < 0.15):
    s1='1'
else:
    s1='0'
    
x2=math.pow(2,2)
y2=1/x2
if(y2 > 0.12 and y2 < 0.15):
    s2='1'
else:
    s2='0'

x3=math.pow(2,3)
y3=1/x3
if(y3 > 0.12 and y3 < 0.15):
    s3='1'
else:
    s3='0'

x4=math.pow(2,4)
y4=1/x4
if(y4 > 0.12 and y4 < 0.15):
    s4='1'
else:
    s4='0'          
    
print(i4,i3,i2,i1,'.',s1,s2,s3,s4)


#I coded like this because I knew already there was a 4.4 fixed point basis 2,and
#I also knew the number(pi)
