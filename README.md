# ParseTree
Sentential Logic is a way of expressing arguments as formula. Well formed formula of Sentential Logic look like this:

**((AvB)->C)**

**(Av(D->A))**

**((A&C)v(D&E))**

Badly formed formula look like this:

**AvB**

**AvBvC**

**C->DAD**

**((Avb)vC)**

**(AvC**

Capital letters represent statements such as "Bob ate an apple" while other symbols such as "v" and "->" are operators. ParseTree checks to see if a given expression of sentential logic is well formed or not. 
