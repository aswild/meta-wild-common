# Dialog segfaults with narrowc ncurses6, use ncursesw instead
EXTRA_OECONF_remove = "--with-ncurses"
EXTRA_OECONF_append = " --with-ncursesw"
