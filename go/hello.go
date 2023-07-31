package main
// variable heap & stack learn,
// run with:
//  go run -gcflags -m xxx.go

import "fmt"

func getPointerOfLocalVar() *int {
    x := 10 // go will put it into heap,
    return &x
}

// heap & stack test,
func heapStackTest() {
    px := getPointerOfLocalVar()
    fmt.Printf("x: %d\n", *px)

    y := 20 // go will put it into stack,
    fmt.Printf("y: %d\n", y)
}

func main() {
    heapStackTest()
}