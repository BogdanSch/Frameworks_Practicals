import requests

def performTask1():
    name: str = input("Enter your name: ")
    print(f"Hello, {name}!")

def performTask2():
    nums: list[int] = []
    for i in range(1, 21):
        nums.append(i)
    
    for num in nums:
        print(f"{num}\t{num ** 2}")

def performTask3():
    def filterEvenNumbers(numbers: list[int]) -> list[int]:
        return [num for num in numbers if num % 2 == 0]
    
    nums: list[int] = []
    for i in range(1, 100):
        nums.append(i)

    evenNumbers: list[int] = filterEvenNumbers(nums)
    print("Even numbers:")
    for num in evenNumbers:
        print(num)

def performTask4():
    currency: str = input("Enter the currency code (e.g., USD, EUR, UAH): ")

    response = requests.get(f"https://api.exchangerate-api.com/v4/latest/{currency}")
    if response.status_code == 200:
        data = response.json()
        print(f"Exchange rates for {currency}:")
    
        for currency, rate in data["rates"].items():
            print(f"{currency}: {rate}")
    else:
        print("Failed to fetch exchange rates.")

def main():
    performTask1()
    performTask2()
    performTask3()
    performTask4()

if __name__ == "__main__":
    main()