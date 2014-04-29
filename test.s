.section .data

_msg_nargs:
	.ascii "Number of arguments supplied: %d\n\0"

_msg_sargs:
	.ascii "  %02d. %s\n\0"

.section .text

.globl _start

_start:

	pushl %ebp
	movl %esp, %ebp

	leal 8(%ebp), %edx
	movl 4(%ebp), %eax

	pushl %edx
	pushl %eax
	call _main
	addl $8, %esp
	movl %eax, %edx

	# restore stack pointer...
	movl %ebp, %esp
	popl %ebp

	# exit
	movl $1, %eax
	movl %edx, %ebx
	int $0x80

_main:

	pushl %ebp
	movl %esp, %ebp
	subl $4, %esp

	# load first argument...
	movl 8(%ebp), %eax
	pushl %eax
	pushl $_msg_nargs
	call printf
	addl $8, %esp

	# initialize counter with zero...
	xorl %eax, %eax
	movl %eax, -4(%ebp)

loop:
	movl 8(%ebp), %edx
	movl -4(%ebp), %ecx
	cmpl %ecx, %edx
	jle loop_exit
	movl 12(%ebp), %edx
	movl (%edx, %ecx, 4), %eax
	incl %ecx
	movl %ecx, -4(%ebp)
	pushl %eax
	pushl %ecx
	pushl $_msg_sargs
	call printf
	addl $12, %esp
	jmp loop

loop_exit:

	# set return value...
	xorl %eax, %eax

	# exit _main function...
	movl %ebp, %esp
	popl %ebp
	ret

