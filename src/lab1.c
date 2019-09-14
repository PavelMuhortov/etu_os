#include <stdlib.h>
#include <stdio.h>

int main (int argc, char **argv, char **env){
	// Вывод переданных приложению параметров
	for (int i=0; i<argc; i++){
		printf("\n%d: %s", i, argv[i]);
	}
	// Вывод разделителя
	printf("\n-----\n");
	// Вывод системных переменных окружения
	for (int i=0; env[i]!=0;i++){
		printf("\n%d: %s\n", i, env[i]);
	}
	// Ожидания ввода символа q для завершения приложения
	int i;
	for (;;){
		i = getchar();
		if (i=='q'){break;}
	}
	return argc;
}
