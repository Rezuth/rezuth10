
include emu8086.inc


org 100h

printn 'Scan a and b to compute their sum'
   
print 'a='
call SCAN_NUM
mov a,cx
        
        
   
printn

print 'b='
call SCAN_NUM
mov b,cx    

add cx,a

printn

mov ax,a
call PRINT_NUM
print '+'
mov AX,b
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