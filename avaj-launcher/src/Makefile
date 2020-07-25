NAME = start
all: $(NAME)
$(NAME):
    @mkdir ./bin
    @javac -d ./bin/ src/**/*.java
    @touch $(NAME)
    @echo 'java -cp ./bin src.controller.Simulation $$1' > $(NAME)
    @chmod +x $(NAME)
fclean:
    @rm -rf ./bin
    @rm $(NAME)
re: fclean all