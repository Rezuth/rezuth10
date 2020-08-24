print('1.The maximum sampling frequency is given my the memory.')
print('2.The minimum sampling frequency is 24kHz,because we can hear audio at maximum 12kHz,and for this we sample twice as much')
print('3.Since the stereo is analog,it is not quantized.We must perform analog to digital conversion A/DC=sampling + quantizing')
print('4.The frequencies over 4kHz will become  indistinguishable')
fs=96000*3600
bits=fs*32
dim=round(bits/8.0/1024/1024/1024,2)
print('5.Dimension is ',dim,' GB.After we discard the other samples,we have ',dim*3/4,' GB(maybe)')