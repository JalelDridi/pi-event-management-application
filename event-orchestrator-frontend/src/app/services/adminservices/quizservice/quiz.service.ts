import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Quiz} from "../models/quiz.model";
@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private baseUrl = 'http://localhost:8080/quiz'; // Adjust this URL to your Spring Boot API endpoint
  private baseUrl2 = 'http://localhost:8080'; // Adjust this URL to your Spring Boot API endpoint


  constructor(private http: HttpClient) { }

  createQuiz(quizData: any): Observable<any> {
    console.log("quizData:", quizData);

    return this.http.post(`${this.baseUrl}`, quizData);
  }

  getQuizzes(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.baseUrl}`);
  }

  getQuizById(id: number): Observable<Quiz> {
    return this.http.get<Quiz>(`${this.baseUrl}/${id}`);
  }

  updateQuiz(id: number, quiz: Quiz): Observable<Quiz> {
    return this.http.put<Quiz>(`${this.baseUrl}/${id}`, quiz);
  }

  submitAnswer(answerQuiz: any): Observable<any> {
    return this.http.post(`${this.baseUrl2}/answerQuiz`, answerQuiz);
  }

  calculateResults(quizId: number, userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/score/${quizId}/${userId}`);
  }

  checkQuizCompletion(quizId: number, userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/check/${quizId}/${userId}`);
  }

}
