
;We use three different interrupts

org 100h

;First:INT 10h / AH = 0Ch-changes color for a single pixel.


mov al, 13h
mov ah, 0
int 10h      ; set graphics video mode. 
mov al, 4H
mov cx,10
mov dx,20

count:
    
    cmp cx,30
        je finish
    mov ah, 0ch
    int 10h ; set pixel. 
    inc cx
    jmp count     

finish:

;Second:INT 21h / AH=5-output character to the printer emulation.    


mov ah, 5
mov dl, 'a'
int 21h

;Third:INT 21h / AH=9 - output of a string at DS:DX. String must be terminated by '$'. 

mov dx, offset s
mov ah, 9
int 21h

ret

s db "salutare $"





