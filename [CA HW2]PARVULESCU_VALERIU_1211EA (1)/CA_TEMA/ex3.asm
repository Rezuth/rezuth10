
include emu8086.inc


org 100h

lea bx,s
sub [bx],32; the position for the uppercase is the lowercase-32(ex:A(65)=a(97)-32) 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]
inc bx
sub [bx],32 
putc [bx]



ret
   
s dw 'salutare'

end





