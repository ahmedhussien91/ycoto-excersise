SUMMARY = "syntax recipe"
DESCRIPTION = "Recipe for testing yocto recipes syntax"
LICENSE = "MIT"

python do_display() {
    print("welcome to syntax testing - printing");
    bb.plain("welcome to syntax testing - plain");
}

addtask display before do_build
