package ru.gb.worktaskmanager.managercore.mappers;

//Интерфейс мапперов
public interface Mapper <T, R>{
    R map(T object);
}
