#pragma once

struct PhoneBook;

PhoneBook* createPhoneBook();
void add(PhoneBook *pb, char number[], char name[]);
void findNumber(PhoneBook *pb, char name[]);
void findName(PhoneBook *pb, char number[]);
void save(PhoneBook *pb);
void deletePhoneBook (PhoneBook *pb);
void print(PhoneBook *pb);
void deleteLast(PhoneBook *pb);
