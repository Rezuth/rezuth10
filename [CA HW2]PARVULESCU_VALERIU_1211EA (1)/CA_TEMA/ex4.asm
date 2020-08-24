
include emu8086.inc

;let's assume the string has length 9
;the 13th line doesn't let the user choose a string
;with length greater than 9
;for the string SalUTaRe! the program
;prints it as sALutArE!

org 100h

mov di,s
mov dx,10 ;string size
print 'the string is:'
call GET_STRING
printn
mov cx,9 
lea si,di
print 'the changed string is:'
loop1:

    mov al,[si] 
    
    cmp al,65  ; char less than 'A',we don't care
    jl nothing2
    cmp al,90
    jg else
    add al,32
    mov [si],al
    putc [si]
    jmp nothing
        
    else:
        
        cmp al,97
        jl nothing2 ;char lesser than 'a' and greater than 'Z',we don't care
        cmp al,122
        jg nothing2 ;char greater than 'z',we don't care
        sub al,32
        mov [si],al
        putc [si]
        jmp nothing
     
    nothing2: ;here we display the non-letter chars
        
         mov [si],al
         putc [si]
         jmp nothing
        
    nothing:
    
         inc si
                
      
loop loop1    
              
ret
 
s dw 'xxxxxxxxx'

DEFINE_GET_STRING

end 