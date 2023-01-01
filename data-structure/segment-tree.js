let tree
let N

function build(a){
    N = a.length * 2
    tree = new Array(n).fill(0)
    buildRec(1, 0, n-1)
    console.log(tree)

}

function buildRec(index, left, right) {
    if(left===right) {
        tree[index] = a[left]
        return
    }
    
    let mid = Math.floor((left+right)/2)
    let nextIndex = index<<1
    let l = buildRec(nextIndex, left, mid)
    let r = buildRec(nextIndex+1, mid+1, right)
    tree[index] = tree[nextIndex] + tree[nextIndex+1]
}

function updateTreeNode(i, value) {
    let diff = value - a[i]
    updateTreeNodeRec(1, diff, 0, n-1, i)
    console.log(tree)
}

function updateTreeNodeRec(index, diff, left, right, i) {
    if(left<=i && right >=i) tree[index] = tree[index] + diff
    
    if(left === right || left>i || right<i) return

    let mid = Math.floor((left+right)/2)
    let nextIndex = index<<1
    updateTreeNodeRec(nextIndex,diff, left, mid, i)
    updateTreeNodeRec(nextIndex+1, diff, mid+1, right, i)
}

function query(i, j) {
    return queryRec(1, 0, n-1, i, j)
}

function queryRec(index,  left, right, i, j) {
    if(left>=j || right<i) return 0
    if(left>=i && right < j) return tree[index]
    
   let mid = Math.floor((left+right)/2)
   let nextIndex = index<<1
   
   return queryRec(nextIndex, left, mid, i, j) + queryRec(nextIndex+1, mid+1, right, i, j)
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
updateTreeNode(2, 1);

// print the sum in range(1,2)
// index-based
console.log(query(1, 3))