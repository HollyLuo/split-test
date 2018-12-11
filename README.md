# split-test

Double Click[Ticket]
Click [Assign = “Assign To Me”]
Click [Actions.Action Type = “Complete”]
Click [Scroll Bar(right)]
Type [Complete Ticket.# of Processed Securities = “5”]
Type [Complete Ticket.# of Securities Added/Updated within Application = “BPS:2 & I-Broker:3”]
Click [Scroll Bar(right)]
Click [Scroll Bar(right)]
Click [Save]
Click [element close “x”]

1 Double Click[Ticket]
2 Click [Assign = “Assign To Me”]
3 Click [Actions.Action Type = “Complete”]
4 Click [Scroll Bar(right)]
5 Type [Complete Ticket.# of Processed Securities = “5”]
6 Type [Complete Ticket.# of Securities Added/Updated within Application = “BPS:2 & I-Broker:3”]
7 Click [Save]
8 Click [element close “x”]

1,2,3,4,5,6,4,4,7,8


Double Click[Ticket]
Click [Actions.Action Type = “Complete”]
Click [Scroll Bar(right)]
Type [Complete Ticket.# of Processed Securities = “2”]
Type [Complete Ticket.# of Securities Added/Updated within Application = “JPS:2”]
Click [Scroll Bar(right)]
Click [Scroll Bar(right)]
Click [Save]
Click [element close “x”]

9 Type [Complete Ticket.# of Processed Securities = “2”]
10 Type [Complete Ticket.# of Securities Added/Updated within Application = “JPS:2”]

1,3,4,9,10,4,4,7,8



1 Double Click[Ticket]
2 Click [Scroll Bar(left)]
3 Click [Assign = “Assign To Me”]
4 Click [Actions.Action Type = “Complete”]
5 Type [Complete Ticket.# of Processed Securities = “?”]
6 Type [Complete Ticket.# of Securities Added/Updated within Application = “?”]
7 Click [Actions.Action Type = “Close”]
8 Click [Save]
9 Click [element close “x”]
10 Click [Close Type = “Cancelled/Duplicate”]

1,7,10,8,9: close ticket
1,(2),3,4,5,6,8,9: assign and complete ticket

1,7,10,8,9,1,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,7,10,8,9,1,2,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,7,10,8,9,1,2,2,3,4,5,6,8,9,1,3,4,5,6,8,9


0:  (1)(7)(10)(8)(9)
1:  (1)(3)(4)(5)(6)(8)(9)
2:  (1)(2)(3)(4)(5)(6)(8)(9)
3:  (1)(2)(3)(4)(5)(6)(8)(9)
4:  (1)(7)(10)(8)(9)
5:  (1)(2)(3)(4)(5)(6)(8)(9)
6:  (1)(2)(3)(4)(5)(6)(8)(9)
7:  (1)(7)(10)(8)(9)
8:  (1)(2)(2)(3)(4)(5)(6)(8)(9)
9:  (1)(3)(4)(5)(6)(8)(9)

  pattern 1:  (1 2 3 4 5 6 8 9 )    support :  1.25 (5/4) sequence ids: 2 3 5 6 8 
  pattern 2:  (1 3 4 5 6 8 9 )    support :  1.75 (7/4) sequence ids: 1 2 3 5 6 8 9 
  pattern 3:  (1 7 10 8 9 )    support :  0.75 (3/4) sequence ids: 0 4 7 
  pattern 4:  (1 8 9 )    support :  2.5 (10/4) sequence ids: 0 1 2 3 4 5 6 7 8 9 



## Ignore content

1 Double Click[Ticket]
2 Click [Scroll Bar(left)]
3 Click [Assign]
4 Click [Actions.Action Type]
5 Type [Complete Ticket.# of Processed Securities = “?”]
6 Type [Complete Ticket.# of Securities Added/Updated within Application = “?”]
8 Click [Save]
9 Click [element close “x”]
10 Click [Close Type = “Cancelled/Duplicate”]

1,4,10,8,9,1,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,4,10,8,9,1,2,3,4,5,6,8,9,1,2,3,4,5,6,8,9,1,4,10,8,9,1,2,2,3,4,5,6,8,9,1,3,4,5,6,8,9

0:  (1)(4)(10)(8)(9)
1:  (1)(3)(4)(5)(6)(8)(9)
2:  (1)(2)(3)(4)(5)(6)(8)(9)
3:  (1)(2)(3)(4)(5)(6)(8)(9)
4:  (1)(4)(10)(8)(9)
5:  (1)(2)(3)(4)(5)(6)(8)(9)
6:  (1)(2)(3)(4)(5)(6)(8)(9)
7:  (1)(4)(10)(8)(9)
8:  (1)(2)(2)(3)(4)(5)(6)(8)(9)
9:  (1)(3)(4)(5)(6)(8)(9)

  pattern 1:  (1 2 3 4 5 6 8 9 )    support :  1.25 (5/4) sequence ids: 2 3 5 6 8 
  pattern 2:  (1 3 4 5 6 8 9 )    support :  1.75 (7/4) sequence ids: 1 2 3 5 6 8 9 
  pattern 3:  (1 4 8 9 )    support :  2.5 (10/4) sequence ids: 0 1 2 3 4 5 6 7 8 9 
  pattern 4:  (1 4 10 8 9 )    support :  0.75 (3/4) sequence ids: 0 4 7 
