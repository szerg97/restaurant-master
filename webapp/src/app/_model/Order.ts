export interface Order{
    id: number,
    price: number,
    timestamp: Date,
    foods: Map<String, number>
};