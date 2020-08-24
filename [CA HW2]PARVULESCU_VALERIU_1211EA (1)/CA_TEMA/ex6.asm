
include emu8086.inc


;we will convert a decimal number to binary(ex:175=10101111)

org 100h

print 'hex number='
mov di,s
mov dx,3 ;string size
call GET_STRING
mov si,di

printn
print 'hex in dec is:'

cmp [si+1],0
    jne convert
mov al,[si]
cmp al,57
    jg else2            ;we check if we introduce a single character,for that we only multiply by 1(16^0)
sub al,'0'              ;this substraction makes the char number into int number
mov bx,1
mul bx
add decimal,ax
jmp finish1

else2:                 ;here we multiplay the first character with 16^1 and second digit with 16^0(ex:AF(175)=10*16^1+15*16^0=175)
    
     cmp al,65 ;A
           je op11
     cmp al,66 ;B
           je op21
     cmp al,67 ;C
           je op31
     cmp al,68 ;D
           je op41
     cmp al,69 ;E
           je op51
     cmp al,70 ;F
           je op61
           
         op11:
        
            mov ax,10
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
           
        op21:
        
            mov ax,11
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
            
        op31:
        
            mov ax,12
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
           
        op41:
         
            mov ax,13
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
            
        op51:
        
            mov ax,14
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
            
        op61:
        
            mov ax,15
            mov bx,1
            mul bx
            add decimal,ax
            jmp finish1
                  
     
     



convert:

     
    mov al,[si]
    cmp al,57
        jg else
    sub al,'0'
    cmp count,0
        je change1
    jmp change2
     
    
    else:
    
        cmp al,65 ;A
            je op1
        cmp al,66 ;B
            je op2
        cmp al,67 ;C
            je op3
        cmp al,68 ;D
            je op4
        cmp al,69 ;E
            je op5
        cmp al,70 ;F
            je op6
            
            
        op1:
        
            mov ax,10
            cmp count,0
                je change1
            jmp change2
           
        op2:
        
            mov ax,11
            cmp count,0
                je change1
            jmp change2
            
        op3:
        
            mov ax,12
            cmp count,0
                je change1
            jmp change2
           
        op4:
         
            mov ax,13
            cmp count,0
                je change1
            jmp change2
            
        op5:
        
            mov ax,14
            cmp count,0
                je change1
            jmp change2
            
        op6:
        
            mov ax,15
            cmp count,0
                je change1
            jmp change2
                  
    change1:    
            
        mov bx,16
        mul bx
        add decimal,ax
        inc count
        inc si
        jmp convert
        
    change2:
    
        mov bx,1
        mul bx
        add decimal,ax
        jmp finish1
              
finish1:    ;finished conversion in dec,now let's start in binary:
   
    mov count,0                
    mov ax,decimal
    call PRINT_NUM
    printn   
    print 'hex in binary is:'
    mov bx,2
    
    loop1:
             
        mov dx,0       ;necessary before looking at the remainder
        div bx          ;the remainder after division is stored in dx and we use it to put it in the stack
        inc count
        push dx
        cmp ax,0
           je next
        jmp loop1
    
    next:
    
        pop dx
        dec count
        mov ax,dx
        call PRINT_NUM
        cmp count,0
            je finish2
        jmp next
        
finish2:

ret

s dw 'xx'  ;1 or 2 characters for the hex number
count dw 0
decimal dw 0 
 
DEFINE_GET_STRING
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end