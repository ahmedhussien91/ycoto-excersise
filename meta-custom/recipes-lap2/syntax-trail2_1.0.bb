SUMMARY = "syntax recipe"
DESCRIPTION = "Recipe for testing yocto recipes syntax"
LICENSE = "MIT"

A = "${B} baz"
B = "${C} bar"
C = "foo"

# uncomment line by line
# *At this point, ${A} equals "foo bar baz"*
#C = "qux"
# *At this point, ${A} equals "qux bar baz"*
#B = "norf"
# *At this point, ${A} equals "norf baz"*

# uncomment this and comment lines above and see the behavior
#A := "${B} baz"
#B := "${C} bar"
#C := "foo"

python do_display() {
    bb.plain("variable A: " + d.getVar("A"));
    bb.plain("variable B: " + d.getVar("B"));
    bb.plain("variable C: " + d.getVar("C"));
}

addtask display before do_build
