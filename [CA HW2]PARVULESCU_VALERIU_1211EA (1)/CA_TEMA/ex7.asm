
include emu8086.inc


;we will convert a decimal number to hexa(ex:175=AF) and binary(ex:175=10101111)

org 100h

print 'dec='
call SCAN_NUM
mov n,cx
lea si,s
printn
print 'dec in hex is:'
cmp n,15
    jg convert2
cmp n,9                  ;here it happens the opposite:
    jle convert1         ;Assume the number is not greater than 255(this is our condition)
cmp ax,10                ;so from the decimal number we substract the greatest multiple of 16 less than the number(the multiples of 16 go up to 240(16*15))
    je op0               ;the number of the multiple we use is the first digit of the hex number
cmp ax,11                ;what remains after substraction is the second digit
    je op1               ;ex:we have the number 175
cmp ax,12                ;the greatest multiple of 16 less than 175 is 160(16*10)
    je op2               ;that means we used the 10th multiple,so first digit is A
cmp ax,13                ;now then:175-160=15-second digit is F(and it's done!)
    je op3               ;and this goes for every number
cmp ax,14                ;we start from the greatest multiple(240=16*15)and go down until we met the first multiple less than the number
    je op4               ;last multiple we test is the first one(16=16*1)
cmp ax,15
    je op5
    
op0:

    mov al,[si]
    mov al,'A'
    putc al
    jmp finish

op1:
    
    mov al,[si]
    mov al,'B'
    putc al
    jmp finish
    
op2:

    mov al,[si]
    mov al,'C'
    putc al
    jmp finish

op3:
    
    mov al,[si]
    mov al,'D'
    putc al
    jmp finish
    
op4:

    mov al,[si]
    mov al,'E'
    putc al
    jmp finish

op5:
    
    mov al,[si]
    mov al,'F'
    putc al
    jmp finish
    
convert1:

    mov ax,n 
    call PRINT_NUM
    jmp finish

convert2:

    mov ax,16
    mov bx,var
    mul bx
    cmp n,ax
        jge next
    dec var
    jmp convert2
    
    next:
        
        mov bx,n
        sub bx,ax
        mov ax,var
        inc count
        cmp ax,9
            jle printagain
        cmp ax,10
            je op01
        cmp ax,11
            je op11
        cmp ax,12
            je op21
        cmp ax,13
            je op31
        cmp ax,14
            je op41
        cmp ax,15
            je op51
                                
        op01:

            mov al,[si]    
            mov al,'A'
            putc al
            inc si
            jmp next2
           
        
        op11:
            
            mov al,[si]    
            mov al,'B'
            putc al
            inc si
            jmp next2
            
        op21:
        
            mov al,[si]    
            mov al,'C'
            putc al
            inc si
            jmp next2
        
        op31:
            
            mov al,[si]    
            mov al,'D'
            putc al
            inc si
            jmp next2
            
        op41:
        
            mov al,[si]    
            mov al,'E'
            putc al
            inc si
            jmp next2
        
        op51:
        
            mov al,[si]    
            mov al,'F'
            putc al
            inc si
            jmp next2
              
            
        printagain:
        
            call PRINT_NUM
            inc si
            jmp next2
            
        next2:
             
             inc count
             mov ax,bx
             cmp ax,9
                jle printagain2
             cmp ax,10
                je op011
             cmp ax,11
                je op111
             cmp ax,12
                je op211
             cmp ax,13
                je op311
             cmp ax,14
                je op411
             cmp ax,15
                je op511
                
             op011:
    
                mov al,[si]    
                mov al,'A'
                putc al
                jmp finish
               
            
             op111:
                
                mov al,[si]    
                mov al,'B'
                putc al             
                jmp finish 
                 
             op211:
            
                mov al,[si]    
                mov al,'C'
                putc al
                jmp finish
            
             op311:
                
                mov al,[si]    
                mov al,'D'
                putc al
                jmp finish 
                
             op411:
            
                mov al,[si]    
                mov al,'E'
                putc al
                jmp finish
            
             op511:
            
                mov al,[si]    
                mov al,'F'
                putc al
                jmp finish
                   
             printagain2:
             
                call PRINT_NUM
                jmp finish
        
        
        
finish:

    printn
    print 'n in binary is:'

    mov ax,n
    mov bx,2

    loop1:
             
        mov dx,0    
        div bx
        inc count2
        push dx
        cmp ax,0
           je next3
        jmp loop1
    
    next3:
    
        pop dx
        dec count2
        mov ax,dx
        call PRINT_NUM
        cmp count2,0
            je finish2
        jmp next3
        
finish2:

ret

n dw ?
s dw 'xx'
count dw 0
count2 dw 0
var dw 15
 
DEFINE_SCAN_NUM
DEFINE_PRINT_NUM
DEFINE_PRINT_NUM_UNS

end


