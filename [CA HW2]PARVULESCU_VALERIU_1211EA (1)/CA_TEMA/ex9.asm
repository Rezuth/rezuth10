
include emu8086.inc


org 100h

printn 'Scan a and b to compute their product'
   
print 'a='
call SCAN_NUM
mov a,cx
           
printn

print 'b='
call SCAN_NUM
mov b,cx    

mov ax,a
mov bx,b
mul bx
mov cx,ax

printn

mov ax,a
call PRINT_NUM
print 'x'
mov ax,b
call PRINT_NUM
print '=' 
mov ax,cx
call PRINT_NUM 
             

ret
   
a dw ?
b dw ?

DEFINE_SCAN_NUM   
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS
  
end