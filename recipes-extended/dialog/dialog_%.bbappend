# Dialog segfaults with narrowc ncurses6, use ncursesw instead
EXTRA_OECONF:remove = "--with-ncurses"
EXTRA_OECONF:append = " --with-ncursesw"
