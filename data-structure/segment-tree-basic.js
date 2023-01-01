let tree
let N

function build(a){
    N = a.length * 2
    tree = new Array(n).fill(0)
    
    for(let i = n;i <N; i++) {
        tree[i] = a[i-n]
    }
    
    for(let i=n-1;i>0;i--) {
        let left = i<<1 //shift left, point to next layer
        let right = left + 1
        tree[i] = tree[left] + tree[right]
    }
    
    console.log(tree)
}

function updateTreeNode(index, value) {
    let i=index+n
    tree[i] = value
    
    while(i>1) { //stop at the layer 2
      let nexti = i>>1
      tree[nexti] = tree[i] + (i&1>0? tree[i-1]: tree[i+1])
      i = nexti
    }
    console.log(tree)
}

function query(left, right) {
    left = n + left
    right = n + right
    
    let result = 0
    
    while(left < right){
        //odd number, move right by 1
        if(left&1 >0) result = result + tree[left++]
        if(right&1 >0) result = result + tree[--right]
        left = left>>1
        right = right>>1
        
    }
    
    return result
}
  
let a = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    
// n is global
n = a.length;

// build tree 
build(a);

// print the sum in range(1,2)
// index-based
console.log(query(1, 3))

// modify element at 2nd index
updateTreeNode(2, 1)

// print the sum in range(1,2)
// index-based
console.log(query(1, 3))