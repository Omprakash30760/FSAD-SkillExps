import { render, screen } from "@testing-library/react";
import App from "./App";

test("renders skill 11 dashboard heading", () => {
  render(<App />);
  const heading = screen.getByText(/Skill 11 - React API Integration/i);
  expect(heading).toBeInTheDocument();
});
